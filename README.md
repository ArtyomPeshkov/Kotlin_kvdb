# Руководство по менеджеру баз данных
## Проект 2: key-value база данных
### О программе
Данный проект реализует менеджер баз данных, который позволяет создавать key-value базы данных и работать с ними.

Сначала вам будет предложено выбрать базу данных с которой вы хотите работать (вы всегда можете создать новую базу данных
или удалить одну из имеющихся)

После выбора базы данных вы сможете начать работать с ней, чтобы узнать список команд, необходимо прописать 'list'. Обратите внимание на то,
что изменения сохраняются вручную, поэтому не стоит делать слишком много изменений без промежуточных сохранений.

Базы данных сохраняются в папку dataBases(в корне проекта)

#### !!!ВНИМАНИЕ!!! Крайне не рекомендуется добавлять файлы в папку dataBases и менять файлы, которые там хранятся. Это может привести к ошибкам в программе и/или утере ваших данных


### Запуск
Вам достаточно собрать проект и потом запустить файл main.kt, программа подскажет вам, как действовать дальше.

### Тестирование
Всё необходимое для тестирования находится в папке src\test. В папке src\test\resources лежат файлы для тестирования основных функций.
Тест всей программы происходит с помощью TestAll
