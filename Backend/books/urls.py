from django.urls import path, include
from rest_framework.routers import DefaultRouter
from rest_framework_simplejwt.views import (
    TokenObtainPairView,
    TokenRefreshView,
    TokenVerifyView,
    TokenBlacklistView,
)
from knox import views as knox_views
from .views import *

router = DefaultRouter()
router.register(r'authors', AuthorViewSet)
router.register(r'editorials', EditorialViewSet)
router.register(r'genre', GenreViewSet)
router.register(r'book', BookViewSet)
router.register(r'users', UsersLibrotekaViewSet)
router.register(r'orders', OrdersViewSet)



urlpatterns = [
    path('registro/', RegisterAPI.as_view(), name='register'),
    path('login/', LoginAPI.as_view(), name='login'),
    path('logout/', knox_views.LogoutView.as_view(), name='logout'),
    path('logoutall/', knox_views.LogoutAllView.as_view(), name='logoutall'),
    path('roles/', RoleListCreateAPIView.as_view(), name='role-list-create'),
    path('roles/<int:pk>/', RoleRetrieveUpdateDestroyAPIView.as_view(), name='role-retrieve-update-destroy'),
    path('buscar-libros/', BusquedaLibrosView.as_view(), name='buscar_libros'),
    path('users/', UsersLibrotekaListCreate.as_view(), name='users-list-create'),
    path('libros/', LibrosView.as_view(), name='libros'),
    path('create-orders/', CreateOrderView.as_view(), name='create-orders'),
    path('favorites/', FavoriteManageView.as_view(), name='favorites'),
    path('favorites/<int:pk>/', DeleteFavoriteView.as_view(), name='delete_favorite'),
    path('ratings/', RatingManageView.as_view(), name='post_rating'),
    path('ratings/<int:pk>/', ModifyRatingView.as_view(), name='modify_rating'),
    path('user/update/', UpdateUserAPI.as_view(), name='user-update'),
    path('api/token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh')
    path('api/token/verify/', TokenVerifyView.as_view(), name='token_verify'),
    path('api/token/blacklist/', TokenBlacklistView.as_view(), name='token_blacklist'),



    path('', include(router.urls)),
]
