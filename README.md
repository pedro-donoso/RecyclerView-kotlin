1. En res creo package layout con archivo activity_main.xml y lo configuro:

```
<?xml version="1.0" encoding="utf-8"?>
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

2. Configuro MainActivity.kt

```
   class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
```

3. Creo data class SuperHero.kt

   ```
   data class SuperHero(
    val superhero: String,
    val publisher: String,
    val realName: String,
    val photo: String
)
```


