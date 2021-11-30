# Synergy_Project2
Synergy's Project 2 Repository

## Jennica's Rules to the GitHub
- To merge to the main repository you need permission from one other.
- Commits should be logical and/or informative.

## Steps from new branch to merge
1. Create new branch:
   ```
   $ git checkout -b <new_branch_name>
   ```
2. 

### Some ways to not run into merging problems:
- `git status`: displays the state of the working directory and the staging area.
- `git log`: displays committed snapshots. It lets you list the project history, filter it, and search for specific changes.
- `git log --merge`: argument to the git log command will produce a log with a list of commits that conflict between the merging branches.
- `git diff main <your_branch_name>`: compares the two branches and shows the differences bettween the two. 
