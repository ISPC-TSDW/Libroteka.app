from django.contrib import admin
from .models import *


class BookAdmin(admin.ModelAdmin):
    list_display = ('title', 'id_Author', 'id_Genre', 'id_Editorial', 'description', 'price', 'stock')

class AuthorAdmin(admin.ModelAdmin):
    list_display = ('name', 'country')

class EditorialAdmin(admin.ModelAdmin):
    list_display = ('name',)

class GenreAdmin(admin.ModelAdmin):
    list_display = ('name',)

class OrderStatusAdmin(admin.ModelAdmin):
    list_display = ('status',)

class OrderAdmin(admin.ModelAdmin):
    list_display = ('id_Order_Status', 'date', 'books', 'total', 'books_amount')


class UsersLibrotekaAdmin(admin.ModelAdmin):
    list_display = ('username', 'first_name', 'last_name', 'dni', 'email', 'password')

class FavoriteAdmin(admin.ModelAdmin):
    list_display = ('id', 'id_user', 'id_book', 'created_at')

class RatingAdmin(admin.ModelAdmin):
    list_display = ('id', 'id_user', 'id_book', 'rating', 'created_at', 'updated_at')


admin.site.register (UsersLibroteka, UsersLibrotekaAdmin)
admin.site.register(Book, BookAdmin)
admin.site.register(Author, AuthorAdmin)
admin.site.register(Editorial, EditorialAdmin)
admin.site.register(Genre, GenreAdmin)
admin.site.register(Order, OrderAdmin)
admin.site.register(OrderStatus, OrderStatusAdmin)
admin.site.register(Favorite, FavoriteAdmin)
admin.site.register(Rating, RatingAdmin)
