# Bundle A: Tickets 1 to 13 (Zuko)

The World split and the protocol split. **This lands first. Everything
else depends on it.**

## Delete
```
git rm src/main/java/za/co/wethinkcode/robots/server/Handlers.java
git rm src/main/java/za/co/wethinkcode/robots/server/Router.java
git rm src/test/java/za/co/wethinkcode/robots/server/HandlersTest.java
git rm src/test/java/za/co/wethinkcode/robots/server/RouterTest.java
```

## Copy
```
cp -r src .                              # bash
Copy-Item .\src -Destination . -Recurse -Force   # PowerShell
```

## Note on two files in here

`ConsoleListener.java` and `Server.java` are **intermediate versions**.
`ConsoleListener` is the original file with only the result-type
renames from Ticket 2 applied, still 294 lines with all fourteen
commands in it. `Server.java` has the `Router` import but not the
console one. Bundle B replaces both. This is deliberate so your bundle
compiles and passes on its own without needing anyone else's work.

`WorldTest.java` and `MineTest.java` are likewise the Phase 1 versions,
still one big file each. Bundle C splits them.

## One test needed an import, not just a move

`ClientHandlerTest` used `Router` with no import, because it was in the
same package. Router moved to `server.protocol`, so that test stops
compiling. The fixed file is in here. Without it the **compile stage
fails**, not the test stage, so the pipeline dies at step one.

## Verify then push
```
mvn -q clean test && make test-own && make test-reference
```
Push before anyone else starts. B and C cannot compile until this is on
`main`.
