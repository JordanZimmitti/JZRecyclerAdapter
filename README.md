# JZRecyclerAdapter
Generic RecyclerAdapter For The Android RecyclerView

#### How To Access JZRecyclerAdapter In Your Android App

Add these repositories to the android part of your app build.gradle

    // Repositories For App//
    repositories {

        // Github Repository//
        mavenCentral()

        // Maven Repository Url//
        maven { url 'https://jitpack.io' }
    }
   
Add This line of code to the dependencies in your build.gradle (replace x.x.x with newest version below in green) 

    implementation 'com.github.JZDevelopers:JZUtils:x.x.x'    
[![](https://jitpack.io/v/JordanZimmitti/JZRecyclerAdapter.svg)](https://jitpack.io/#JordanZimmitti/JZRecyclerAdapter)
#### Using JZRecyclerAdapter

JZRecyclerAdapter is a generic adapter for a RecyclerView. To use the adapter first create a RrecylerView.

    <!-- RecyclerView recyclerView -->
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:overScrollMode="never"
        android:paddingStart="16dip"
        android:paddingEnd="16dip"
        android:layout_alignParentStart="true"
        android:layout_below="@id/titleActivity"
        android:layout_height="match_parent"
        android:layout_width="match_parent"/>
        
The following code is how to create the JZRecyclerAdapter. The first parameter is for the layout you want the data to be shown in. The second paramater is the list containing the data of any given type.

    val arrayOfInts = ArrayList<Int>()
    arrayOfInts.add(1)
    arrayOfInts.add(2)
    arrayOfInts.add(3)

    // Defines And Instantiates The JZRecyclerAdapter//
    val intAdapter = JZRecyclerAdapter(R.layout.custom_layout, arrayOfInts) {

          // Gets The Data From The List To Show Its Value In A TextView//
          textView.text = arrayOfInts.toString()
    }

To set the spacing between each item in the RecyclerView use the following line of code

    // Adds Bottom Padding After Each Item In The RecyclerView//
    recyclerView.addItemDecoration(JZRecyclerAdapter.Spacing(60))
    
To setup JZRecyclerAdapter with your RecyclerView use the following lines of code

    // Shows All The Integers In The List//
    recyclerView.hasFixedSize()
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter       = intAdapter

To add an on click listener to the items in the RecyclerView use the following lines of code

    intAdapter.setItemClick { position, view ->
            
    }
    
JZRecylerAdapter also has a built in multi-selection tool. To setup multi-selection use the following lines of code

    // What Happens When An Item Is Multi-Selected (Long Clicked)
    JZRecyclerAdapter.multiSelected(userAdapter) { itemSelected, allItemSelected, isSelected ->
        
        when {

            // When There Are No Items Selected//
            allItemSelected == 0 -> {

            }

            // When One Or More Items Is Selected//
            isSelected -> {

            }

            // When An Item is De-Selected//
            !isSelected -> {

            }
        }
    }
