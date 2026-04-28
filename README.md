# Client-Listing-Desktop-Application  
  
> A simple java project within 3 days to save data about name, email, phone, and address

## Prerequisite

- JDK v17 or higher. (use `java --version` to check)

## How to Run

### Linux/MacOS

#### Method 1 (GUI)

1. Right click on file
2. Click "Properties" section
3. Click "permissions" tab
4. Add file permission to execute (+x)
5. Double click on `runApp.sh`

#### Method 2 (CLI)

```bash
bash runApp.sh
```

#### Method 3 (CLI)

```bash
chmod +x runApp.sh
./runApp.sh
```

### Windows

#### Method (GUI)

- Double click on `runApp.bat`

## Additional Information

### Data Location

- `bin/ClientList.csv`
- `backup/clientdb.mv.db`

### How to Get All Data from H2 Database

```bash
java -cp "lib/h2-2.2.224.jar" org.h2.tools.Shell -url "jdbc:h2:./backup/clientdb" -user sa -sql "SELECT * FROM clients"
```
