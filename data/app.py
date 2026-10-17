import csv
import random
from datetime import datetime, timedelta

random.seed(42)  # воспроизводимость

CATEGORIES = ["Electronics", "electronics", "Books", "books ", "Clothing", "Food"]
REGIONS    = ["North", "South", "West", "East", "north "]
STATUSES   = ["paid", "shipped", "cancelled", "PAID", "pending"]

def random_date(start: datetime, days: int) -> str:
    d = start + timedelta(days=random.randint(0, days))
    # иногда ломаем формат
    if random.random() < 0.03:
        return "invalid-date"
    return d.strftime("%Y-%m-%d")

def generate(rows: int = 1000, path: str = "sales.csv"):
    start = datetime(2024, 1, 1)
    with open(path, "w", newline="", encoding="utf-8") as f:
        writer = csv.writer(f)
        writer.writerow(["order_id", "customer_name", "category",
                         "region", "quantity", "price", "order_date", "status"])

        for i in range(1, rows + 1):
            order_id      = i
            customer_name = random.choice(["Alice", "  Bob", "Charlie ", "Diana", "Eve", ""])
            category      = random.choice(CATEGORIES)
            region        = random.choice(REGIONS)
            quantity      = random.choice([1, 2, 3, 5, 10, -1, 0])  # отрицательные/нули
            price         = round(random.uniform(5, 500), 2)
            order_date    = random_date(start, 365)
            status        = random.choice(STATUSES)

            writer.writerow([order_id, customer_name, category, region,
                             quantity, price, order_date, status])

            # ~5% дубликатов
            if random.random() < 0.05:
                writer.writerow([order_id, customer_name, category, region,
                                 quantity, price, order_date, status])

    print(f"Сгенерировано {rows}+ строк в {path}")

if __name__ == "__main__":
    generate(rows=1000, path="sales.csv")