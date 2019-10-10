# Pal Tracker Distributed - HTTPie

This article documents how to run PAL Tracker Distributed application
requests with HTTPie.

See [HTTPie Docs](https://httpie.org/doc) for more information.

## Create and Verify User

### Register New User

```bash
http -v POST :8083/registration 'Accept: application/json' 'Content-Type: application/json' name=Pete
```

### Get User by Id

```bash
http -v :8083/users/${USER_ID} 'Accept: application/json' 'Content-Type: application/json'
```

### Get Accounts by Owner Id

```bash
http -v :8083/accounts?ownerId=${USER_ID} 'Accept: application/json'
```

## Create and Verify Project

### Add New Project

```bash
http -v POST :8083/projects 'Accept: application/json' 'Content-Type: application/json' name='Basket Weaving' accountId=${ACCOUNT_ID}
```

### Get Projects by Account Id

```bash
http -v :8083/projects?accountId=${ACCOUNT_ID} 'Accept: application/json' 'Content-Type: application/json'
```

## Create and Verify Time Entry

### Add New Time Entry

```bash
http -v POST :8084/time-entries 'Accept: application/json' 'Content-Type: application/json' projectId=${PROJECT_ID} userId=${USER_ID} date='2015-05-17' hours=6
```

### Get Time Entries by User Id

```bash
http -v :8084/time-entries?userId=${USER_ID} 'Accept: application/json' 'Content-Type: application/json'
```