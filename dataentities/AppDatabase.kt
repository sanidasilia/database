package com.example.supermarket.dataentities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Product::class, ShoppingListItem::class, WishlistItem::class, PurchaseHistory::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ProductDao(): ProductDao
    abstract fun ShoppingListDao(): ShoppingListDao
    abstract fun WishlistDao(): WishlistDao
    abstract fun PurchaseHistoryDao(): PurchaseHistoryDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
