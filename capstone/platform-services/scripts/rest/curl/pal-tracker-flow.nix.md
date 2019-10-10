# Pal Tracker Distributed - *Nix 

This article documents how to run PAL Tracker Distributed application
requests with cURL.

See [cURL Docs](https://curl.haxx.se/) for more information.

Note the environment variable placeholders,
such as `${USER_ID}`, `${ACCOUNT_ID}`, etc.
They are not automatically correlated between requests,
so you will have to manually.

Extra credit if you author shell scripts to do the correlation!

## Create and Verify User

### Register New User

```bash
curl -i -XPOST -H"Content-Type: application/json" -H"Accept: application/json" localhost:8083/registration -d'{"name": "Pete"}'
```

### Get User by Id
```bash
curl -i -H"Accept: application/json" localhost:8083/users/${USER_ID}
```

### Get Accounts by Owner Id

```bash
curl -i -H"Accept: application/json" localhost:8083/accounts?ownerId=${USER_ID}
```

## Create and Verify Project

### Add New Project

```bash
curl -i -XPOST -H"Content-Type: application/json" -H"Accept: application/json" localhost:8083/projects -d'{"name": "Basket Weaving", "accountId": ${ACCOUNT_ID}}'
```

### Get Projects by Account Id

```bash
curl -i -H"Accept: application/json" localhost:8083/projects?accountId=${ACCOUNT_ID}
```

## Create and Verify Time Entry

### Add New Time Entry

```bash
curl -i -XPOST -H"Content-Type: application/json" -H"Accept: application/json" localhost:8084/time-entries/ -d'{"projectId": ${PROJECT_ID}, "userId": ${USER_ID}, "date": "2015-05-17", "hours": 6}'
```

### Get Time Entries by User Id

```bash
curl -i -H"Accept: application/json" localhost:8084/time-entries?userId=${USER_ID}
```