------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: miniTunes (DOPO Lab02)

PURPOSE OF PROJECT:
miniTunes is a simplified music playlist manager. It lets users create
and manage named playlists, and perform unary and binary set-like
operations (add, delete, subtract, union, intersection, difference) on
their songs.

VERSION or DATE: 1.0 - 2026-08-30

HOW TO START THIS PROJECT:
Open the project (src folder) in BlueJ. It has no main method: it is
used by creating instances of MiniTunes and Playlist from BlueJ, or
by running the MiniTunesTest and PlaylistTest unit tests.

AUTHORS:
Ivan Bernal
Cesar Malaver

USER INSTRUCTIONS:
1. Create a MiniTunes object.
2. define(name) to declare an empty list.
3. assign(name, songs) to assign songs to it.
4. size(name) and toString(name) to query it.
5. assignUnary(a, b, op, values) with op in {'a','d','s'} and
   assignBinary(a, b, op, c) with op in {'u','i','d'} to operate on lists.
6. ok() indicates whether the last operation completed successfully.
