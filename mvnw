#!/bin/sh
# Minimal Maven wrapper: downloads Maven (version from .mvn/wrapper/maven-wrapper.properties)
# into ~/.m2/wrapper on first run, then forwards all arguments to it.
# To replace this with the official wrapper (also creates mvnw.cmd), run:  mvn wrapper:wrapper
set -e
BASE_DIR=$(cd "$(dirname "$0")" && pwd)
URL=$(sed -n 's/^distributionUrl=//p' "$BASE_DIR/.mvn/wrapper/maven-wrapper.properties")
NAME=$(basename "$URL" -bin.zip)
DIR="$HOME/.m2/wrapper/dists/$NAME"
if [ ! -x "$DIR/$NAME/bin/mvn" ]; then
  echo "Downloading $NAME ..."
  mkdir -p "$DIR"
  TMP="$DIR/maven.zip"
  if command -v curl >/dev/null 2>&1; then curl -fsSL "$URL" -o "$TMP"; else wget -q "$URL" -O "$TMP"; fi
  unzip -q -o "$TMP" -d "$DIR" && rm -f "$TMP"
fi
exec "$DIR/$NAME/bin/mvn" "$@"
