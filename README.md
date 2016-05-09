# Bitbucket Command Line Interface

This project gives you a Command Line Interface (CLI) for remotely access [Bitbucket](http://bitbucket.org). This provides an automatically method for adminstrating Bitbucket repository.

[![Build Status](https://travis-ci.org/ldt116/bitbucket-cli.svg?branch=master)](https://travis-ci.org/ldt116/bitbucket-cli)

## Usage

```
Usage: bbcli --action <action> --file <file> --user <username> --pass <password>
  --action <action>
        Requested operation to be performed.
  --file <file>
        The input data file in csv format.
  --user <username>
        Username of Bitbucket account.
  --pass <password>
        Password of Bitbucket account.
```

You can find the full action document in [usage](Usage.txt) document. There are some examples [here](example.md).

### Input format

The input of the action is described in `csv` format. The first line must be the header. For example, the following file includes 1 header row and  2 data rows; there are 3 columns names `repoId`, `scm`, `isPrivate`.

```
repoId, scm, isPrivate
abc, git, true
def, hg, false
```

Note that the columns orders are not important, as long as you provided all necessary parameters for that action.

## License

```
The MIT License (MIT)

Copyright (c) 2016 ThuanLe

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
