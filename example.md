# Bitbucket CLI example

## Create Repo

Input file as `createRepo.csv`
```
repoId, scm, isPrivate
abc, git, true
def, hg, false
```

Command line run with user `ABC` and password `XYZ`
```
bbcli --user ABC --pass XYZ  --file createRepo.csv --action createRepo
```