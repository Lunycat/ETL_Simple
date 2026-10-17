# ETL_Simple

End-to-end ETL-пайплайн: CSV → Spark (Scala) → Parquet, с оркестрацией через Apache Airflow. Всё упаковано в Docker и поднимается одной командой.

## Архитектура

- **Spark**: master + worker (`apache/spark:3.5.1`).
- **Airflow**: webserver + scheduler + Postgres (метаданные).
- **ETL**: Scala 2.12 + Spark 3.5.1, собирается в fat jar через `sbt assembly`.

## Стек

- **Scala** 2.12.18
- **Apache Spark** 3.5.1
- **Apache Airflow** 2.9.3 (Python 3.11)
- **PostgreSQL** 16 (метаданные Airflow)
- **Docker** + **docker compose**
- **sbt** 1.13.0

## Быстрый старт

```bash
# 1. Собрать ETL-jar
sbt clean assembly

# 2. Поднять всё
docker compose up -d --build
```

## Доступ к UI
Сервис	URL	Логин / пароль
1) Spark UI	http://localhost:8080	—
2) Airflow UI	http://localhost:8081	admin / admin

## Требования

- Docker Desktop (или Docker Engine + Compose v2)
- JDK 11+ (для локальной сборки jar)
- sbt
