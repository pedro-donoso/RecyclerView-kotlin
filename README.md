![pantalla](https://github.com/user-attachments/assets/9ba140e8-8b2b-48e6-a268-c1a4d6272a13)

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

7. Creo funciones para iniciar RecyclerView y Seleccionar Item:

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

8. En carpeta layout creo item_superhero.xml con Card View:

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



### Recycler View

- Un RecyclerView es un componente de interfaz de usuario en Android que permite mostrar una lista de elementos de manera eficiente y personalizable. Es una forma de mostrar una gran cantidad de datos en una pantalla, como una lista de artículos, una galería de imágenes, etc.

#### Algunas Características

- Eficacia: El RecyclerView es muy eficiente en términos de rendimiento, ya que solo carga los elementos que se están mostrando en la pantalla en ese momento. Esto significa que no se carga toda la lista de elementos al mismo tiempo, lo que puede ser muy costoso en términos de recursos.

- Personalización: El RecyclerView es muy personalizable, ya que se puede definir cómo se verán los elementos individuales y cómo se comportarán cuando se interactúa con ellos.
  
- Flexibilidad: El RecyclerView se puede utilizar para mostrar una variedad de tipos de datos, desde listas simples hasta grillas complejas.

#### Términos clave

- Adapter: Un adapter es un objeto que se utiliza para proporcionar los datos que se mostrarán en el RecyclerView.
  
- ViewHolder: Un ViewHolder es un objeto que se utiliza para almacenar una referencia a los elementos de la interfaz de usuario que se están mostrando en el RecyclerView.
  
- LayoutManager: Un LayoutManager es un objeto que se utiliza para definir cómo se mostrarán los elementos en el RecyclerView.
  
- Item: Un item es un elemento individual que se muestra en el RecyclerView.
  

