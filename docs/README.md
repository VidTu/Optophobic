<img src="optophobic.png" alt="Optophobic Icon" width=128 height=128/>

# Optophobic

Remove "Light updates disabled" added by Sodium Extra.

## Downloads

- [GitHub Releases](https://github.com/VidTu/Optophobic/releases)

## Dependencies

- Fabric Loader
- Minecraft 1.21.11
- [Sodium](https://modrinth.com/mod/sodium)
- [Sodium Extra](https://modrinth.com/mod/sodium-extra)

## About

When you toggle "Light Updates" in the Sodium Extra mod, it permanently
adds the "Light updates disabled" label without a way of hiding it.
This mod hides this label.

## FAQ

### For Players

**Q**: I need help, have some questions, or have some other feedback.  
**A**: You can join the [Discord server](https://discord.gg/Q6saSVSuYQ).

**Q**: Where can I download this mod?  
**A**: [GitHub Releases](https://github.com/VidTu/Optophobic/releases).
You can also find unstable builds at
[GitHub Actions](https://github.com/VidTu/Optophobic/actions).
You'll need a GitHub account to download these.

**Q**: Which mod loaders are supported?  
**A**: Fabric is supported. Quilt should work too.

**Q**: Which Minecraft versions are supported?  
**A**: Minecraft 1.21.11.

**Q**: Are you planning to support NeoForge/Forge? Other versions?  
**A**: As new versions will release, I will support them for Fabric.
NeoForge/Forge support is not planned currently,
neither is the support for old versions.

**Q**: Do I need Fabric API or Quilt Standard Libraries?  
**A**: No.

**Q**: Is this mod client-side or server-side?  
**A**: This mod works on the client side. Sodium and Sodium Extra
are client-side mods too. There is no server-side version.

**Q**: I've found a bug.  
**A**: Report it [here](https://github.com/VidTu/Optophobic/issues). If you are not
sure whether this is a bug or a simple question, you can join the
[Discord](https://discord.gg/Q6saSVSuYQ). Report security vulnerabilities
[here](https://github.com/VidTu/Optophobic/security).

**Q**: Can I use this in my modpack?  
**A**: Sure. Credit (e.g., a link to the mod's GitHub page) is appreciated but
is not required. Monetization and redistribution are allowed as per the
[MIT License](https://github.com/VidTu/Optophobic/blob/main/LICENSE).

**Q**: Why is this mod not on Modrinth or CurseForge?  
**A**: I believe this is a temporary "hack" and the upstream
developer should add this functionality in some time. Shall I
be wrong, this mod will be uploaded there.

**Q**: Why?  
**A**: I don't like that you can't hide "Light updates disabled" in any way.
I have [asked](https://github.com/FlashyReese/sodium-extra/issues/507) the
developer to add the ability of this, awaiting response at the time of writing.

**Q**: Does it have a performance penalty? How laggy is it?  
**A**: It's not laggy. It's a one-mixin mod that disables the label.

### For Developers

**Q**: Is this mod open source?  
**A**: [Yes.](https://github.com/VidTu/Optophobic) (Licensed
under [MIT License](https://github.com/VidTu/Optophobic/blob/main/LICENSE))

**Q**: Why so much yapping in this README?  
**A**: ~~I paid for the whole LLM, I'm going to use the whole LLM.~~
Because writing READMEs (even though I don't use AI except
for grammar checks) is easier than writing actual code.

**Q**: Do you use AI/LLM/Code Generation/Copilot/etc.?  
**A**: Except for the aforementioned grammar checkers above, no. I use only
the laggiest IntelliJ IDEA inspections based on the buggiest algorithms.
Can't be sure for contributors, but most of the code is written
by the project author. Also, I don't care about AI personally.

**Q**: Does Optophobic have a public API?  
**A**: Nope. There's no public-facing API in this mod.
All classes/packages are marked as
[@ApiStatus.Internal](https://javadoc.io/static/org.jetbrains/annotations/26.0.2/org/jetbrains/annotations/ApiStatus.Internal.html)
for that reason.

**Q**: Can I still *link*/compile against to the mod? What about the
[SemVer](https://semver.org/) versioning used by the mod?  
**A**: You can, at your own risk. SemVer-compatible versioning is used by
Optophobic for ease of use, but it is used arbitrarily. This mod does not
declare a public API, therefore, breaking source/binary changes may and will
occur even between minor and patch versions.

## License

This project is provided under the MIT License.
Check out [LICENSE](https://github.com/VidTu/Optophobic/blob/main/LICENSE)
for more information.

## Credits

This mod is developed primarily by [VidTu](https://github.com/VidTu),
but it wouldn't be possible without:

- [Contributors](https://github.com/VidTu/HCsCR/graphs/contributors).
- [Fabric Loom](https://github.com/FabricMC/fabric-loom) by
  [FabricMC](https://github.com/FabricMC). (and contributors)
- [Fabric Loader](https://github.com/FabricMC/fabric-loader) by
  [FabricMC](https://github.com/FabricMC). (and contributors)
- [Sodium](https://github.com/CaffeineMC/sodium) by
  [CaffeineMC](https://github.com/CaffeineMC). (and contributors)
- [Sodium Extra](https://github.com/FlashyReese/sodium-extra) by
  [FlashyReese](https://github.com/FlashyReese). (and contributors)
- [Mixin](https://github.com/SpongePowered/Mixin) by
  [SpongePowered](https://github.com/SpongePowered). (and contributors)
- [Minecraft](https://minecraft.net/) by
  [Mojang](https://mojang.com/).

It also uses [Gradle](https://gradle.org/) and [Java](https://java.com/).

## Development

### Building (Compiling)

To compile the mod from the source code:

1. Have 1 GB of free RAM, 10 GB of free disk space,
   and an active internet connection.
2. Install Java 21 (and/or 25) and dump it into PATH and/or JAVA_HOME.
3. Run `./gradlew assemble` from the terminal/PowerShell.
4. Grab the JAR from the `./build/libs/` folder.

### Developing/Debugging

Run the `./gradlew runClient` command to launch the game client. You can
attach a debugger to that process. Hotswap is supported. "Enhanced" hotswap
(class redefinition) and hotswap agent will work if supported by your JVM.

Running the client via generated tasks (e.g., for IntelliJ IDEA) may work, but
you might need to make some adjustments. Launching the game directly
(without Gradle) might also work, but it is also not supported.

The development environment has stricter preconditions: Mixin checks,
Netty detector, Java assertions, etc. Code with bugs might (and probably will)
fail faster here than in a production environment.

The recommended IDE for development is IntelliJ IDEA (Community or Ultimate)
with the Minecraft Development plugin. This is not a strict requirement,
however. Any IDE/editor should work just fine.
