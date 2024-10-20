from django.urls import path, include
from rest_framework.routers import DefaultRouter
from knox import views as knox_views

from .views import AuthorViewSet, BookViewSet, OrdersViewSet, CreateOrderView, BusquedaLibrosView, EditorialViewSet, GenreViewSet,  BookViewSet, GenreViewSet, GetBooksByAuthorOrGenreOrTitleView, RegisterAPI,RoleRetrieveUpdateDestroyAPIView, RoleListCreateAPIView, UsersLibrotekaListCreate, UsersLibrotekaViewSet, LibrosView, LoginAPI



router = DefaultRouter()
router.register(r'authors', AuthorViewSet)
router.register(r'editorials', EditorialViewSet)
router.register(r'genre', GenreViewSet)
router.register(r'book', BookViewSet)
router.register(r'users', UsersLibrotekaViewSet)
router.register(r'orders', OrdersViewSet)

# router.register(r'ordersStatus', OrderStatusViewSet)
# router.register(r'create-orders', CreateOrderView)

urlpatterns = [
    path('registro/', RegisterAPI.as_view(), name='register'),
    # path('login/', login_view, name='login_view'),
    # path('login/', LoginAPI, name='login_view'),
    path('login/', LoginAPI.as_view(), name='login'),
    path('logout/', knox_views.LogoutView.as_view(), name='logout'),
    path('logoutall/', knox_views.LogoutAllView.as_view(), name='logoutall'),
    # path('usuarios/', UserListView.as_view(), name='user-list'),
    path('roles/', RoleListCreateAPIView.as_view(), name='role-list-create'),
    path('roles/<int:pk>/', RoleRetrieveUpdateDestroyAPIView.as_view(), name='role-retrieve-update-destroy'),
    # path('user-libroteka/', UserLibrotekaAPI.as_view(), name='user-libroteka'),
    path('buscar-libros/', BusquedaLibrosView.as_view(), name='buscar_libros'),
    path('users/', UsersLibrotekaListCreate.as_view(), name='users-list-create'),
    path('libros/', LibrosView.as_view(), name='libros'),
    path('create-orders/', CreateOrderView.as_view(), name='create-orders'),
    # path('orders/', OrdersViewSet.as_view(), name='orders'),

    path('', include(router.urls)),
]
