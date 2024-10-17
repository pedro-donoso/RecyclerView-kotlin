![pantalla](https://github.com/user-attachments/assets/db6b2224-797c-4aad-8e47-6514e83a05f6)

### Creación de la App

1. Configurar gradle(Module:app) para utilizar viewBinding:

```
buildFeatures {
        compose = true
        viewBinding = true
    }
    dataBinding {
        enable = true
    }
```

2. Agrego las dependencias a utilizar (glide, cardview, constraintlayout)

```
dependencies {
...
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.glide)
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
...
}
```

3. En res -> new directory layout y en layout -> new Layout Resource File (Convierto a FrameLayout y creo recyclerview)

```
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerSuperHero"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</FrameLayout>
```

4. Creo data class

```
data class SuperHero(
    val superhero: String,
    val publisher: String,
    val realName: String,
    val photo: String
)
```

5. Creo SuperHeroProvider (con datos del superheroe/ina)

```
class SuperHeroProvider {
    companion object {
        val superHeroList = listOf<SuperHero>(
            SuperHero(
                "Batman",
                "DC",
                "Bruce Wayne",
                "https://www.thedigitalfix.com/wp-content/sites/thedigitalfix/2022/12/batman-big-part-in-dceu.jpg"
            ),
            ...
```

6. Convierto MainActivity a AppCompatActivity:

```
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }
```

7. En AndroidManifest.xml configuro Theme de la Activity y agrego Permiso de Internet:
```
 <uses-permission android:name="android.permission.INTERNET"/>
        ...
        <activity
            android:name="MainActivity"
            android:exported="true"
            android:label="@string/app_name"
            android:theme="@style/Theme.AppCompat.DayNight.DarkActionBar">
        ...
        </activity>
    </application>
```

8. Creo funciones para iniciar RecyclerView y Seleccionar Item:

```
    private fun initRecyclerView(){
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.recyclerSuperHero.layoutManager = manager
        binding.recyclerSuperHero.adapter =
            SuperHeroAdapter(SuperHeroProvider.superHeroList) { superhero ->
            onItemSelected(
                superhero
            )
        }

    }

    fun onItemSelected(superHero: SuperHero){
        Toast.makeText(this, superHero.superhero, Toast.LENGTH_SHORT).show()
    }
}
```

9. En carpeta layout creo item_superhero.xml con Card View:

```
<androidx.cardview.widget.CardView
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_marginHorizontal="16dp"
        android:layout_marginVertical="8dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <ImageView
            android:id="@+id/ivSuperHero"
            android:layout_width="180dp"
            android:layout_height="180dp"
            android:contentDescription="imagen"
            android:scaleType="centerCrop"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            tools:background="@color/black"
            tools:ignore="HardcodedText" />

        <TextView
            android:id="@+id/tvSuperHeroName"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintEnd_toEndOf="parent"
            android:textSize="21sp"
            android:textStyle="bold"
            android:textColor="@color/black"
            app:layout_constraintStart_toEndOf="@id/ivSuperHero"
            app:layout_constraintTop_toTopOf="parent"
            tools:text="KotlinMan" />

        <TextView
            android:id="@+id/tvRealName"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toEndOf="@id/ivSuperHero"
            app:layout_constraintTop_toBottomOf="@id/tvSuperHeroName"
            tools:text="Pedro" />

        <TextView
            android:id="@+id/tvPublisher"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            android:layout_margin="16dp"
            tools:text="Hola" />

    </androidx.constraintlayout.widget.ConstraintLayout>

</androidx.cardview.widget.CardView>
```
10 - En raíz del proyecto creo directorio SuperHeroAdapter.kt:

```
class SuperHeroAdapter(
    private val superHeroList: List<SuperHero>,
    private val onClickListener: (SuperHero) -> Unit
) : RecyclerView.Adapter<SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superHeroList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = superHeroList.size
}
```

11 - En raíz del proyecto creo directorio SuperHeroViewHolder.kt:

```
class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun render(superHeroModel: SuperHero, onClickListener: (SuperHero) -> Unit) {
        binding.tvSuperHeroName.text = superHeroModel.superhero
        binding.tvRealName.text = superHeroModel.realName
        binding.tvPublisher.text = superHeroModel.publisher
        Glide.with(binding.ivSuperHero.context).load(superHeroModel.photo).into(binding.ivSuperHero)

        itemView.setOnClickListener { onClickListener(superHeroModel) }
    }
}
```
