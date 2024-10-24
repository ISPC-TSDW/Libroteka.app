from rest_framework import serializers, status
from .models import *
from django.contrib.auth.models import User
from django.contrib.auth.hashers import make_password

class TokenObtainPairResponseSerializer(serializers.Serializer):
    access = serializers.CharField()
    refresh = serializers.CharField()

    def create(self, validated_data):
        raise NotImplementedError()

    def update(self, instance, validated_data):
        raise NotImplementedError()
    
class TokenRefreshResponseSerializer(serializers.Serializer):
    access = serializers.CharField()

    def create(self, validated_data):
        raise NotImplementedError()

    def update(self, instance, validated_data):
        raise NotImplementedError()
    

class TokenVerifyResponseSerializer(serializers.Serializer):
    def create(self, validated_data):
        raise NotImplementedError()

    def update(self, instance, validated_data):
        raise NotImplementedError()
    
class TokenBlacklistResponseSerializer(serializers.Serializer):
    def create(self, validated_data):
        raise NotImplementedError()

    def update(self, instance, validated_data):
        raise NotImplementedError()




class AuthorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Author
        fields = ['id_Author', 'name']

class EditorialSerializer(serializers.ModelSerializer):
    class Meta:
        model = Editorial
        fields = ['id_Editorial', 'name']

class GenreSerializer(serializers.ModelSerializer):
    class Meta:
        model = Genre
        fields = ['id_Genre', 'name']

class BookSerializer(serializers.ModelSerializer):
    id_Author = AuthorSerializer()
    id_Genre = GenreSerializer()
    id_Editorial = EditorialSerializer()
    avg_rating = serializers.FloatField(read_only=True)

    class Meta:
        model = Book
        fields = ['id_Book', 'title', 'id_Author', 'id_Genre', 'id_Editorial', 'description', 'price', 'stock', 'avg_rating']

        def create(self, validated_data):
                author_data = validated_data.pop('id_Author', None)
                genre_data = validated_data.pop('id_Genre', None)
                editorial_data = validated_data.pop('id_Editorial', None)

                try:
                    author = Author.objects.get(name=author_data['name'])
                    genre = Genre.objects.get(name=genre_data['name'])
                    editorial = Editorial.objects.get(name=editorial_data['name'])
                except (Author.DoesNotExist, Genre.DoesNotExist, Editorial.DoesNotExist):
                    # Si no se encuentra, crear nuevos registros
                    author = Author.objects.create(**author_data)
                    genre = Genre.objects.create(**genre_data)
                    editorial = Editorial.objects.create(**editorial_data)

                # Verificar si el libro ya existe
                existing_book = Book.objects.filter(title=validated_data['title']).first()
                if existing_book:
                    raise serializers.ValidationError({"detail": "Este libro ya está registrado."})

                # Crear el libro con los IDs obtenidos o nuevos si no existían
                book = Book.objects.create(
                    id_Author=author,
                    id_Genre=genre,
                    id_Editorial=editorial,
                    **validated_data
                )
                return book
        
        def update(self, instance, validated_data):
            author_data = validated_data.get('id_Author', None)
            genre_data = validated_data.get('id_Genre', None)
            editorial_data = validated_data.get('id_Editorial', None)

            if author_data:
                author_serializer = AuthorSerializer(instance.id_Author, data=author_data)
                if author_serializer.is_valid(raise_exception=True):
                    author_serializer.save()

            if genre_data:
                genre_serializer = GenreSerializer(instance.id_Genre, data=genre_data)
                if genre_serializer.is_valid(raise_exception=True):
                    genre_serializer.save()

            if editorial_data:
                editorial_serializer = EditorialSerializer(instance.id_Editorial, data=editorial_data)
                if editorial_serializer.is_valid(raise_exception=True):
                    editorial_serializer.save()

            instance.description = validated_data.get('description', instance.description)
            instance.price = validated_data.get('price', instance.price)
            instance.stock = validated_data.get('stock', instance.stock)

            for attr, value in validated_data.items():
                setattr(instance, attr, value)

            instance.save()

            return instance
        
class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['id', 'username', 'email', 'first_name', 'last_name', 'dni']
class UserLibrotekaSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['id', 'username', 'email', 'first_name', 'last_name', 'dni'] 

    def create(self, validated_data):
        user = User.objects.create_user(
            validated_data['username'], 
            validated_data['email'], 
            validated_data['password']
        )
        return user        

class RegisterSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('id', 'username', 'email', 'password', 'dni')
        extra_kwargs = {'password': {'write_only': True}}

    def create(self, validated_data):
        user = User.objects.create_user(
            username=validated_data['username'], 
            email=validated_data['email'], 
            password=validated_data['password'],
            dni=validated_data.get('dni') 
        )
        return user


class RoleSerializer(serializers.ModelSerializer):
    class Meta: 
        model = Role
        fields = ['id', 'name', 'description']     



class UsersLibrotekaSerializer(serializers.ModelSerializer):
    password = serializers.CharField(write_only=True)

    class Meta:
        model = UsersLibroteka
        fields = ['username', 'first_name', 'last_name', 'dni', 'email', 'password', 'is_active']

    def create(self, validated_data):
        validated_data['password'] = make_password(validated_data['password'])
        return super().create(validated_data)
    
class UserUpdateSerializer(serializers.ModelSerializer):
    username = serializers.CharField(required=False)
    last_name = serializers.CharField(required=False)
    dni = serializers.CharField(required=False)
    password = serializers.CharField(required=False, write_only=True)
    is_active = serializers.BooleanField(required=False)
    class Meta:
        model = UsersLibroteka
        fields = ['username', 'first_name', 'last_name', 'dni', 'email', 'password',  'is_active']
        extra_kwargs = {
            'email': {'read_only': True}, 
            'password': {'write_only': True},
        }
    def update(self, instance, validated_data):
        password = validated_data.pop('password', None)
        if password:
            instance.set_password(password)
        return super().update(instance, validated_data)


class LoginSerializer(serializers.Serializer):
    email = serializers.EmailField()
    password = serializers.CharField(write_only=True)


class OrderSerializer(serializers.ModelSerializer):
    class Meta:
        model = Order
        fields = '__all__'

class FavoriteSerializer(serializers.ModelSerializer):
    class Meta:
        model = Favorite
        fields = '__all__'

    def validate(self, data):

        if not data['id_user'].is_active:
            raise serializers.ValidationError({"detail": "User account is deactivated."})
        return data

class RatingSerializer(serializers.ModelSerializer):
    class Meta:
        model = Rating
        fields = '__all__'

    def validate(self, data):

        if not data['id_user'].is_active:
            raise serializers.ValidationError({"detail": "User account is deactivated."})
        return data
