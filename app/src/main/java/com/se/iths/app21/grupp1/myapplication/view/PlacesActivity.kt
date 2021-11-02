package com.se.iths.app21.grupp1.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.se.iths.app21.grupp1.myapplication.adapter.DescriptionRecyclerAdapter
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.se.iths.app21.grupp1.myapplication.model.Comments
import com.se.iths.app21.grupp1.myapplication.model.Place
import com.se.iths.app21.grupp1.myapplication.databinding.ActivityPlacesBinding
import com.se.iths.app21.grupp1.myapplication.model.Places
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_places.*
import java.util.*


class PlacesActivity : AppCompatActivity() {

    lateinit var binding: ActivityPlacesBinding

    var commentslist : MutableList<Comments> = mutableListOf()
    var placeslist : MutableList<Place> = mutableListOf()
    lateinit var auth: FirebaseAuth
    lateinit var db : FirebaseFirestore
    lateinit var storage : FirebaseStorage
    var mAdapter: DescriptionRecyclerAdapter? = null
   // lateinit var recyclerDescription: RecyclerView
    lateinit var collectionRef: CollectionReference
    var TAG: String = "!!!"
    var lat : Double? = null
    var long : Double? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()
        mAdapter = DescriptionRecyclerAdapter(this, commentslist)
        recyclerDescription.layoutManager = LinearLayoutManager(this)
        recyclerDescription.setHasFixedSize(true)
        recyclerDescription.adapter = mAdapter
        var comments = mutableListOf<Comments>()

       // val lat = intent.getDoubleExtra("lat", 0.0)
        //val long = intent.getDoubleExtra("long",0.0)

        val docId = intent.getStringExtra("docId")

        Log.d("!!!", "{$docId}")

        Log.d("!!!", "Det funkar!")

        // Måste också skicka med placesId från MapsActivity
       // val commentslist = intent.getStringExtra("placeId")



       // val intent = Intent(this, MapsActivity::class.java)

        getPlacesInfo()
      //  getComments()

        binding.buttonSaveDescription.setOnClickListener {
            saveDescription()
        }

    }

   private fun getPlacesInfo() {

       val docId = intent.getStringExtra("docId")
      // val places = hashMapOf<String, Any>()
      // val comments = hashMapOf<String, Any>()

       val uuid = UUID.randomUUID()
       val imageName = "$uuid"
       val reference = storage.reference
       val imageReference = reference.child("images").child(imageName)


       if (docId != null) {
           db.collection("Places").document(docId)
               .get()
               .addOnSuccessListener {  task ->
                   if (task != null)
                        {
                            val place = task.toObject(Places::class.java)
                            binding.placeName.text = place!!.name
                            binding.showLandText.text = place!!.land



                                        db.collection("Places").document(docId)
                                            .get()
                                            .addOnCompleteListener {
                                                if (task != null)
                                                {

                                                val result: StringBuffer = StringBuffer()



                                                if(it.isSuccessful) {

                                                        var url = result.append(it.result!!.data!!.getValue("image")).toString()
                                                        Log.d("!!!", "$url, hello")
                                                        Picasso.get().load(url).into(showImage)



                                                }
                                                }
                                            }


                           // binding.showRBar.numStars = place!!.rating
                           /* places["land"] = binding.showLandText.text.toString()
                            places["date"] = Timestamp.now()
                            places["rating"] = binding.showRBar.numStars
                            places["image"] = showImage.toString()
                            comments["comment"] = binding.recyclerDescription

                            */

                        }

               }
       }
   }

       /* db.collection("Places").document("docId")
.get()
.addOnSuccessListener {

if (it.isEmpty) {
    Toast.makeText(this, "No place found", Toast.LENGTH_LONG).show()
    return@addOnSuccessListener
}



    for (document in it) {
       val placeModel = document.toObject(Place::class.java)
        placeslist.add(placeModel)
        Log.d("!!!", "Document: $document")
    }

}

*/


                   /*  fun getComments() {

        db.collection("Comments").whereEqualTo("placeId", placeId)
            .get()
            .addOnSuccessListener {
                if(it.isEmpty) {
                    Toast.makeText(this, "No comments found", Toast.LENGTH_LONG).show()
                    return@addOnSuccessListener
            }
                for(document in it) {
                    val commentsModel = document.toObject(Comments::class.java)
                    commentslist.add(commentsModel)
                }
                binding.recyclerDescription.apply{
                   mAdapter
               }
    }

    }

   */



                   fun saveDescription() {
                       val places = hashMapOf<String, Any>()

                       if (auth.currentUser != null) {
                           places["beskrivning"] = binding.addDescriptionText.text.toString()

                           db.collection("Places").add(places).addOnSuccessListener {
                               finish()
                           }.addOnFailureListener {
                               Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                           }
                       }
                   }
               }


