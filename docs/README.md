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

**Q**: Is this mod open source?  
**A**: [Yes.](https://github.com/VidTu/Optophobic) (Licensed
under [MIT License](https://github.com/VidTu/Optophobic/blob/main/LICENSE))

**Q**: Do I need Fabric API or Quilt Standard Libraries?  
**A**: Yes, you'll need Fabric API for Fabric and QFAPI/QSL for Quilt.

**Q**: I've found a bug.  
**A**: Report it [here](https://github.com/VidTu/Optophobic/issues). If you are not
sure whether this is a bug or a simple question, you can join the
[Discord](https://discord.gg/Q6saSVSuYQ). Report security vulnerabilities
[here](https://github.com/VidTu/Optophobic/security).

**Q**: Can I use this in my modpack?  
**A**: Sure. Credit (e.g., a link to the mod's GitHub page) is appreciated but
is not required. Monetization and redistribution are allowed as per the
[MIT License](https://github.com/VidTu/Optophobic/blob/main/LICENSE).

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
