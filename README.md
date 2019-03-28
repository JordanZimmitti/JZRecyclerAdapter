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
        
The Following Code Is How To Create The JZRecyclerAdapter. The first parameter is for the layout you want the data to be shown in. The second paramater is the list containing the data of any given type.

    val intAdapter = JZRecyclerAdapter(R.layout.custom_layout, arrayOfInts) {

          // Gets The Data From The List And Saves Its Value
          textView.text = arrayOfInts.toString()
    }
       
