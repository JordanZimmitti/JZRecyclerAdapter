package me.jordanzimmitti.jz_recycler_adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidecirillo.multichoicerecyclerview.MultiChoiceAdapter

// Type Aliases For Differing Actions To Another Class//
private typealias ItemClicked = (position: Int, view: View)                                    -> Unit
private typealias MultiSelect = (itemSelected: Int, allItemSelected: Int, isSelected: Boolean) -> Unit
private typealias Scroll      = (dy: Int)                                                      -> Unit

/** Kotlin Class JZRecyclerAdapter
 *
 * Class That Creates An Adapter To Show All The Items In A RecyclerView List For A Given Type
 *
 * @author Jordan Zimmitti
 *
 * @version 1.0
 */
class JZRecyclerAdapter<TYPE>(private val layoutId: Int,
                              private val list    : List<TYPE>,
                              private val views   : View.(TYPE) -> Unit): MultiChoiceAdapter<JZRecyclerAdapter<TYPE>.ViewHolder>() {

    // Defines ItemClicked Variable//
    private var itemClicked: ItemClicked? = null

    /**.
     * Configures Static Variables And Functions
     */
    companion object {

        // Defines multiSelect And scroll Variables//
        private var multiSelect: MultiSelect? = null
        private var scroll     : Scroll?      = null

        /**.
         * Function That Gets The Code For When The RecyclerAdapter Is Multi Selected
         *
         * @param recyclerAdapter Any recycler adapter
         * @param multiSelect     The code for when the recycler adapter is multi selected
         */
        fun multiSelected(recyclerAdapter: JZRecyclerAdapter<*>, multiSelect: MultiSelect) {

            // Sets The Value For The Class Variable//
            this.multiSelect = multiSelect

            // Starts Function//
            onMultiSelected(recyclerAdapter)
        }

        /**.
         * Function That Gets The Code For When The RecyclerView Is Scrolling
         *
         * @param scroll       The code for when the recycler view is scrolling
         * @param recyclerView Any recycler view
         */
        fun whenScrolling(recyclerView: RecyclerView, scroll: Scroll) {

            // Sets The Value For The Class Variable//
            this.scroll = scroll

            // Starts Function//
            onScrolled(recyclerView)
        }

        /**.
         * Function That Handles When The RecyclerView Is Scrolling
         *
         * @param recyclerView Any recycler view
         */
        private fun onScrolled(recyclerView: RecyclerView) {

            // When The RecyclerView Is Scrolling//
            recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    // The Code When Scrolling//
                    scroll?.invoke(dy)
                }
            })
        }

        /**.
         * Function That Handles When An Item Is MultiSelected
         *
         * @param recyclerAdapter Any recycler adapter
         */
        private fun onMultiSelected(recyclerAdapter: JZRecyclerAdapter<*>) {

            // When An Item Is Multi Selected//
            recyclerAdapter.setMultiChoiceSelectionListener (object: MultiChoiceAdapter.Listener     {

                // When An Item Is Selected//
                override fun OnItemSelected(selectedPosition: Int, itemSelectedCount: Int, allItemCount: Int) {

                    // The Code For When An Item Is Selected//
                    multiSelect?.invoke(selectedPosition, itemSelectedCount, true)
                }

                // When An Item Is De-Selected//
                override fun OnItemDeselected(deselectedPosition: Int, itemSelectedCount: Int, allItemCount: Int) {

                    // The Code For When An Item Is De-Selected//
                    multiSelect?.invoke(deselectedPosition, itemSelectedCount, false)
                }

                // Not Implemented Yet...//
                override fun OnSelectAll(itemSelectedCount: Int, allItemCount: Int) {
                }
                override fun OnDeselectAll(itemSelectedCount: Int, allItemCount: Int) {
                }
            })
        }
    }

    /**.
     * Function That Gets The Amount Of Items Saved In The List
     *
     * @return The amount of items
     */
    override fun getItemCount(): Int {

        // Returns The Amount Of Items//
        return list.size
    }

    /**.
     * Function That Creates And Inflates A Custom View
     *
     * @param parent The parent view
     *
     * @return The custom view
     */
    override fun onCreateViewHolder(parent: ViewGroup, a: Int): JZRecyclerAdapter<TYPE>.ViewHolder {

        // Returns The Custom View//
        return ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))
    }

    /**.
     * Function That Handles When A List Item Is Clicked
     *
     * @param holder   Where the views are defined and initialized
     * @param position Where the specific item is located in the list
     *
     * @return The on click action
     */
    override fun defaultItemViewClickListener(holder: ViewHolder?, position: Int): View.OnClickListener? {

        // Returns The On Click Action//
        return View.OnClickListener { view ->

            // Code For When The Item Is Clicked//
            itemClicked?.invoke(position, view)
        }
    }

    /**.
     * Function that Sets The List's Items To Their Corresponding View
     *
     * @param holder   Where the views are defined and initialized
     * @param position Where the specific Item is located in the list
     */
    override fun onBindViewHolder(holder: JZRecyclerAdapter<TYPE>.ViewHolder, position: Int) {

        // Differs To Parent Class//
        super.onBindViewHolder(holder, position)

        // Sets The Item To Its Corresponding View//
        holder.itemView.views(list[position])
    }

    /**.
     * Function That Gets The Code For When An Item is Clicked
     *
     * @param itemClick The code for when the item is clicked
     */
    fun setItemClick(itemClick: ItemClicked) {

        // Sets The Value For The Class Variable//
        this.itemClicked = itemClick
    }

    /**.
     * Class That Specifies The Amount Of White Space Between Each Item
     *
     * @param height The amount of white space between each item
     */
    class Spacing(private val height: Int): RecyclerView.ItemDecoration() {

        /**.
         * Function that controls The Amount Of Spacing Between Items
         *
         * @param spacing the amount of spacing between the items
         */
        override fun getItemOffsets(spacing: Rect, view: View, parent: RecyclerView, c: RecyclerView.State) {

            // Sets The Spacing//
            spacing.bottom = height

            // Sets The Spacing For The Last Item//
            if (parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1) spacing.bottom = 280
        }
    }

    /**.
     * Class That Sets Up The Views To Show The Lists Items
     *
     * @param customView Custom View That Shows The Lists Items
     */
    inner class ViewHolder(customView: View): RecyclerView.ViewHolder(customView)
}