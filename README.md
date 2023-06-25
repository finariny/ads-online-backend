
# Дипломная работа Skypro "Бэкенд-часть сайта объявлений на Java"


---
### Разработчики
- [Кирилл Вохминов](https://github.com/KaerLaende)
- [Павел Турлачев](https://github.com/turchev)
- [Айкануш Арутюнян](https://github.com/rafaelovna)
- [Анастасия Драгомирова](https://github.com/finariny)
- [Юрий Калынбаев](https://github.com/YURIYKALYNBAEV)

---
## Описание проекта
Сайт объявлений с комментариями.
Под готовый [фронтенд](https://github.com/BizinMitya/front-react-avito) сайта реализована бэкенд часть проекта платформы по перепродаже вещей.
Пользователи могут размещать объявления товаров и оставлять комментарии к другим объявлениям.

## Бэкенд-часть проекта предполагает реализацию следующего функционала:

- Авторизация и аутентификация пользователей.
- Распределение ролей между пользователями: пользователь и администратор.
- CRUD для объявлений на сайте: администратор может удалять или редактировать все объявления, а пользователи — только свои.
- Под каждым объявлением пользователи могут оставлять отзывы.
- В заголовке сайта можно осуществлять поиск объявлений по названию.
- Показывать и сохранять картинки объявлений.

---
## Стек технологий
* **Backend**:
    - Java 11
    - Maven
    - Spring Boot
    - Spring Web
    - Spring Data JPA
    - Spring Security
    - Stream API
    - REST
    - GIT
    - Swagger
    - Lombok
    - MapStruct
    - Liquibase
* **Database**:
    - PostgreSQL    
* **[Frontend](https://github.com/BizinMitya/front-react-avito)**:
    - React
    - Node.js

## Сборка и запуск проекта

1. Склонируйте проект:
* `git clone git@github.com:finariny/ads-online-backend.git`
2. При необходимости измените параметры доступа к базе данных в файлах:
* `docker-compose.yml`, 
* `init-db.sql`, 
* `application.properties`  
3. Перейдите в каталог для развертывания:
* `cd deployment/`
4. Запустите Docker контейнеры Database и Frontend:
* `docker-compose -f docker-compose.yml up -d`
5. Перейдите в папку с проектом:
*  `cd ../../ads-online-backend/`
4. Запустите проект:
* `mvn spring-boot:run`

