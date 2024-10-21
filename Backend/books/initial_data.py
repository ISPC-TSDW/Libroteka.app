from django.db import migrations
from .models import *

def load_initial_data(apps, schema_editor):
    Author = apps.get_model('books', 'Author')
    Genre = apps.get_model('books', 'Genre')
    Editorial = apps.get_model('books', 'Editorial')
    Books = apps.get_model('books', 'Book')

    if not Author.objects().exists():

        authors_data = [
            {"name": "Gabriel García Márquez", "country": "Colombia"},
            {"name": "Isabel Allende", "country": "Chile"},
            {"name": "J.K. Rowling", "country": "Reino Unido"},
            {"name": "J.R.R. Tolkien", "country": "Reino Unido"},
            {"name": "Ernest Hemingway", "country": "Estados Unidos"},
        ]

        authors = {}
        for author_data in authors_data:
            author, created = Author.objects.get_or_create(**author_data)
            authors[author_data['name']] = author


    if not Editorial.objects().exists():

        editorials_data = [
            {"name": "Editorial Planeta"},
            {"name": "Anagrama"},
            {"name": "Penguin Random House"},
            {"name": "Ediciones Salamandra"},
        ]

        editorials = {}
        for editorial_data in editorials_data:
            editorial, created = Editorial.objects.get_or_create(**editorial_data)
            editorials[editorial_data['name']] = editorial


    if not genre.objects().exists():

        genres_data = [
            {"name": "Novela"},
            {"name": "Ficción"},
            {"name": "Ciencia Ficción"},
            {"name": "Fantástico"},
            {"name": "Biografía"},
        ]

        genres = {}
        for genre_data in genres_data:
            genre, created = Genre.objects.get_or_create(**genre_data)
            genres[genre_data['name']] = genre


    if not Books.objects().exists():

        books_data = [
            {
                "title": "Cien años de soledad",
                "id_Author": authors["Gabriel García Márquez"],
                "id_Genre": genres["Novela"],
                "description": "Una obra maestra de la literatura.",
                "price": 29.99,
                "stock": 100,
                "id_Editorial": editorials["Anagrama"],
                "avg_rating": 0.0,
            },
            {
                "title": "La casa de los espíritus",
                "id_Author": authors["Isabel Allende"],
                "id_Genre": genres["Novela"],
                "description": "Una novela que explora la historia de una familia chilena.",
                "price": 19.99,
                "stock": 150,
                "id_Editorial": editorials["Editorial Planeta"],
                "avg_rating": 0.0,
            },
            {
                "title": "Harry Potter y la piedra filosofal",
                "id_Author": authors["J.K. Rowling"],
                "id_Genre": genres["Ficción"],
                "description": "El inicio de la saga de Harry Potter.",
                "price": 25.00,
                "stock": 200,
                "id_Editorial": editorials["Penguin Random House"],
                "avg_rating": 0.0,
            },
            {
                "title": "El hobbit",
                "id_Author": authors["J.R.R. Tolkien"],
                "id_Genre": genres["Fantástico"],
                "description": "Una aventura fantástica en la Tierra Media.",
                "price": 22.50,
                "stock": 175,
                "id_Editorial": editorials["Penguin Random House"],
                "avg_rating": 0.0,
            },
            {
                "title": "El viejo y el mar",
                "id_Author": authors["Ernest Hemingway"],
                "id_Genre": genres["Ficción"],
                "description": "Una historia sobre la lucha entre el hombre y la naturaleza.",
                "price": 18.00,
                "stock": 80,
                "id_Editorial": editorials["Anagrama"],
                "avg_rating": 0.0,
            },
        ]

        for book_data in books_data:
            book, created = Book.objects.get_or_create(title=book_data['title'], defaults=book_data)

class Migration(migrations.Migration):

    dependencies = [
        ('books', '0016_auto_20241019_1234'),
    ]

    operations = [
        migrations.RunPython(load_initial_data),
    ]
