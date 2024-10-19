from django.db import models
from django.contrib.auth.models import AbstractUser, Group, Permission
from django.core.validators import RegexValidator
from jsonfield import JSONField
import json
from django.conf import settings

class User(AbstractUser):
    first_name = models.CharField(max_length=30)
    last_name = models.CharField(max_length=35)
    dni = models.CharField(max_length=10, validators=[RegexValidator(r'^\d{1,10}$')])
    email = models.EmailField(primary_key=True, unique=True)

    groups = models.ManyToManyField(Group, related_name='books_users')
    user_permissions = models.ManyToManyField(Permission, related_name='books_users')

    class Meta:
        db_table = 'users'
        verbose_name = "Usuario"
        verbose_name_plural = "Usuarios"
    def __str__(self):
        return f"{self.first_name} {self.last_name}"

class Role(models.Model):
    name = models.CharField(max_length=100, unique=True)
    description = models.TextField()    

class Author(models.Model):

    id_Author = models.AutoField(primary_key=True)
    name = models.CharField(max_length=50)
    country= models.CharField(max_length=50)

    class Meta:
        db_table= 'author'
        verbose_name = "Autor"
        verbose_name_plural = "Autores"

    def __unicode__(self):
        return self.name

    def __str__(self):
        return self.name
    
class Editorial(models.Model):

    id_Editorial = models.AutoField(primary_key=True)
    name = models.CharField(max_length=100, default='Anagrama')

    
    class Meta:
        db_table= 'editorial'
        verbose_name = "Editorial"
        verbose_name_plural = "Editoriales"

    def __unicode__(self):
        return self.name

    def __str__(self):
        return self.name
    
class Genre(models.Model):

    id_Genre = models.AutoField(primary_key=True)
    name = models.CharField(max_length=100, default='Novela')

    
    class Meta:
        db_table= 'genre'
        verbose_name = "Género"
        verbose_name_plural = "Géneros"

    def __unicode__(self):
        return self.name

    def __str__(self):
        return self.name
class Book(models.Model):

    id_Book = models.AutoField(primary_key=True)
    title = models.CharField(max_length=100)
    id_Author = models.ForeignKey(Author, to_field='id_Author', on_delete=models.CASCADE, blank=True, null=True)
    id_Genre = models.ForeignKey(Genre, to_field='id_Genre', on_delete=models.CASCADE, blank=True, null=True)
    description= models.TextField(max_length=1500)
    price= models.DecimalField(blank=False, decimal_places=2, max_digits=10)
    stock= models.IntegerField(blank=False, default=1000)
    id_Editorial = models.ForeignKey(Editorial, to_field='id_Editorial', on_delete=models.CASCADE, blank=True, null=True)
    
    class Meta:
        db_table= 'book'
        verbose_name = "Libro"
        verbose_name_plural = "Libros"

        def __unicode__(self):
            return self.title

        def __str__(self):
            return self.title
        
    
class OrderStatus(models.Model):
    class Status(models.TextChoices):
        PENDING = ('Pendiente')
        PAID = ('Pago aprobado')
        PREPARING = ('En preparación')
        SENT = ('Enviado')
        RECEIVED = ('Recibido')

    id_Order_Status = models.AutoField(primary_key=True)
    status = models.CharField(
        choices=Status.choices,
        default=Status.PENDING,
        max_length=15
    )

    class Meta:
        db_table= 'OrderStatus'
        verbose_name = "Estado de la orden"
        verbose_name_plural = "Estados de las órdenes"

    def __unicode__(self):
        return self.status

    def __str__(self):
        return self.status



class UsersLibroteka(models.Model):
    username = models.CharField(max_length=30, unique=True)
    first_name = models.CharField(max_length=30)
    last_name = models.CharField(max_length=35)
    dni = models.CharField(max_length=10, validators=[RegexValidator(r'^\d{1,10}$')], unique=True)
    email = models.EmailField(primary_key=True, unique=True)
    password = models.CharField(max_length=128)

    
    class Meta:
        db_table= 'UsersLibroteka'
        verbose_name = "Usuario"
        verbose_name_plural = "Usuarios"

        def __str__(self):
            return f"{self.first_name} {self.last_name} ({self.username})"
    
class Order(models.Model):

    id_Order = models.AutoField(primary_key=True)
    id_Order_Status = models.ForeignKey(OrderStatus, to_field='id_Order_Status', on_delete=models.CASCADE)   
    id_User = models.ForeignKey(UsersLibroteka, to_field='email', null=True, blank=True, on_delete=models.CASCADE)
    date = models.DateTimeField()
    books = models.JSONField(default=list)
    total = models.DecimalField(blank=False, decimal_places=2, max_digits=10)
    books_amount = models.IntegerField(blank=False)
    
    # def save(self, *args, **kwargs):
    #     # Serialize the list of books to JSON
    #     self.books = json.dumps(list(self.books))
    #     super().save(*args, **kwargs)
    class Meta:
        db_table= 'Order'
        verbose_name = "Orden"
        verbose_name_plural = "Órdenes"

    def __unicode__(self):
        return self.id_Order

    def __str__(self):
        return self.id_Order