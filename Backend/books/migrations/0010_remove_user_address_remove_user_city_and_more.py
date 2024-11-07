# Generated by Django 4.2 on 2024-06-02 05:17

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('books', '0009_merge_20240602_0513'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='user',
            name='address',
        ),
        migrations.RemoveField(
            model_name='user',
            name='city',
        ),
        migrations.RemoveField(
            model_name='user',
            name='province',
        ),
        migrations.RemoveField(
            model_name='user',
            name='telephone',
        ),
        migrations.AlterField(
            model_name='book',
            name='description',
            field=models.TextField(max_length=1500),
        ),
        migrations.AlterField(
            model_name='user',
            name='first_name',
            field=models.CharField(max_length=30),
        ),
        migrations.AlterField(
            model_name='user',
            name='is_staff',
            field=models.BooleanField(default=False, help_text='Designates whether the user can log into this admin site.', verbose_name='staff status'),
        ),
        migrations.AlterField(
            model_name='user',
            name='last_name',
            field=models.CharField(max_length=35),
        ),
    ]