# Bitbucket CLI example

## Create Repo

```
repoId, scm,  isPrivate
abc, git, true
def, hg, false
```

```
bbcli --user ABC --pass XYZ  --file createRepo.csv --action createRepo
```