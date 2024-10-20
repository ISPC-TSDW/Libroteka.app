# Libroteka.app

## Integrantes:
* Germán Gustavo Cano
* Romina Sol Haag
* Daiana Soledad Zabala
* Juan Pablo Suarez
* Giuliana Gesto
* Walter Facundo Salvatierra
* Augusto Dizanzo
* Valentina Tommasini


# Tecnicatura Superior en Desarrollo web y Aplicaciones Digitales
## Programación de Aplicaciones Móviles 1

- Equipo: 8
- Proyecto: Libroteka
- Cohorte: 2022 - 2023

Dependencies:
- Android: "x"
- Java: "^17"
- Python: "^3.8"
- Django: "^4.2"
- MYSQL: "^8.0"

Puntos claves:
- Formulario IEEE830: [Link](https://github.com/ISPC-TSDW/Libroteka.app/wiki/Formulario-IEEE830#link-para-acceder-al-formulario)
- Ceremonias - Scrum: [Link](https://github.com/ISPC-TSDW/Libroteka.app/wiki/SPRINT-0)
- Historias de Usuario: [Link](https://github.com/ISPC-TSDW/Libroteka.app/wiki/Historias-de-Usuario)
- Milestones: [Link](https://github.com/ISPC-TSDW/Libroteka.app/milestones)
- Branching Strategy:

| Branch	         | Naming Convention   |
|------------------|---------------------|
| Master           | 	"main"             |
| Release    	     | "release"           |
| Desarrollo	     | "develop"           |
| Rama Integrantes | "iniciales-feature" |

- Link al repositorio web: [Link](https://github.com/ISPC-TSDW/Libroteka.web)

---


<tr>
<th> BackEnd </th>
</tr>
<tr>
<td>

## Credenciales Django Admin
- user: superadmin
- password: libroteka

## Librerías
- Mobile: Java, Android Studio
- BackEnd: django, djangorestframework, django-cors-headers, Pillow, jsonfield, mysqlclient

## Correr localmente

Clone the project

```bash
  git clone https://github.com/ISPC-TSDW/Libroteka.app.git
```

Go to the project directory

```bash
  cd Backend/Libroteka
```

Activate Virtual environment

```bash
# Windows users: create your branch, delete the linux folder '.backendLibroteka-env' as it has linux/mac configuration and you must create a virtual environment for Windows to install the requirements, remember not to include '.backendLibroteka-env' in your commits.
1. python -m venv .backendLibroteka-env
2. .backendLibroteka-env\Scripts\activate

# Linux users
source backendLibroteka-env/bin/activate 
```

Install Libraries

```bash
  pip install -r requirements.txt
```
## If you made changes to models.py:

```bash 
  python manage.py makemigrations
```
## Executes the necessary operations to synchronize the models with the database tables, such as creating new tables, modifying columns, deleting tables, etc.

```bash
  python manage.py migrate
```

Start the server

```bash
  python manage.py runserver
```
</td> 
</tr> 



