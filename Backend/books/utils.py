from django.db.models import Avg
from rest_framework.exceptions import NotFound
from .models import Book, Rating

def update_average_rating(book_id):
    try:
        book = Book.objects.get(id_Book=book_id)
        ratings = Rating.objects.filter(id_book=book_id)

        if ratings.exists():
            avg_rating = ratings.aggregate(Avg('rating'))['rating__avg']
            book.avg_rating = round(float(avg_rating), 2) if avg_rating is not None else 0.0

        else:
            book.avg_rating = 0.0
        book.save()
        print(f"El promedio de calificación del libro {book_id} es: {avg_rating}")

    except Book.DoesNotExist:
        raise NotFound(detail="Book not found.")
