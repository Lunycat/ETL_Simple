from airflow import DAG
from airflow.providers.apache.spark.operators.spark_submit import SparkSubmitOperator
from datetime import datetime, timedelta

with DAG(
    dag_id="etl_simple",
    start_date=datetime(2026, 1, 1),
    schedule="@daily",
    catchup=False,
    default_args={
            "retries": 2,
            "retry_delay": timedelta(minutes=5),
        },
    tags=["etl", "spark"],
) as dag:

    run_etl = SparkSubmitOperator(
        task_id="run_etl",
        conn_id="spark_default",
        application="/opt/airflow/jars/etl_simple.jar",
        java_class="etl.Main",
        application_args=[
            "--input", "/app/data/sales.csv",
            "--output", "/app/out/{{ ds }}",
        ],
        name="etl_simple_{{ ds }}",
        verbose=True,
    )