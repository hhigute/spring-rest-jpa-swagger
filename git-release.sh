#!/bin/bash

# brew upgrade gh

BRANCH=$(git rev-parse --abbrev-ref HEAD)
REPO_URL=$(git config --get remote.origin.url)
RE="^(https|git)(:\/\/|@)([^\/:]+)[\/:]([^\/:]+)\/(.+).git$"
DRAFT_RELEASE=false

if [[$REPO_URL =~ $RE]]; then
  PROTOCOL=${BASH_REMATCH[1]}
  SEPARATOR=${BASH_REMATCH[2]}
  HOSTNAME=${BASH_REMATCH[3]}
  USER=${BASH_REMATCH[4]}
  REPO=${BASH_REMATCH[5]}
fi

while [[ -z "$TAG_VERSION" ]]
do
  read -p "Enter the tag version (e.g.: V1.0.0): " TAG_VERSION
  echo
done

read -p "Enter tag description (e.g.: preview release): " TAG_DESCRIPTION
echo

read -p "Draft release? [y/n] " -n 1 -r
if [[ $REPLY =~ ^[Yy]$ ]]
then
    DRAFT_RELEASE=true
fi
echo


display_data() {
    echo -e Arguments: \
      '\n\tRepository = '$REPO \
      '\n\tBranch = '$BRANCH \
      '\n\tTag Version = '$TAG_VERSION \
      '\n\tDescription = '$TAG_DESCRIPTION \
      '\n\tDraft release = '$DRAFT_RELEASE
}

generate_post_data()
{
  cat <<EOF
{
  "tag_name": "$TAG_VERSION",
  "target_commitish": "$branch",
  "name": "$TAG_VERSION",
  "body": "$TAG_DESCRIPTION",
  "draft": $DRAFT_RELEASE,
  "prerelease": $DRAFT_RELEASE
}
EOF
}

display_data
read -p "Are you sure? [y/n] " -n 1 -r
echo
if [[ $REPLY =~ ^[Yy]$ ]]
then
  curl --data "$(generate_post_data)" "https://api.github.com/repos/$USER/$REPO/releases"
fi
