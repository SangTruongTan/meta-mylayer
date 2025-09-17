DESCRIPTION = "Simple dynamic C library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://arith.c \
    file://print.c \
    file://mydynamic.c \
    file://mylib.h \
"

# Names/versions
MYLIB       = "libmydynamic"
MYMAJOR     = "1"
MYFULLVER   = "1.0.0"

S = "${UNPACKDIR}"
B = "${WORKDIR}/build"

do_compile() {
    bbdebug 1 "Compiling objects"
    mkdir -p ${B}

    # Compile PIC objects (no LDFLAGS on -c)
    ${CC} ${CPPFLAGS} ${CFLAGS} -fPIC -I${S} -c ${S}/arith.c -o ${B}/arith.o
    ${CC} ${CPPFLAGS} ${CFLAGS} -fPIC -I${S} -c ${S}/print.c -o ${B}/print.o

    # Link shared library with SONAME
    ${CC} -shared ${LDFLAGS} \
        -Wl,-soname,${MYLIB}.so.${MYMAJOR} \
        -o ${B}/${MYLIB}.so.${MYFULLVER} \
        ${B}/arith.o ${B}/print.o

    # Symlinks (relative)
    ln -sf ${MYLIB}.so.${MYFULLVER} ${B}/${MYLIB}.so.${MYMAJOR}
    ln -sf ${MYLIB}.so.${MYMAJOR}   ${B}/${MYLIB}.so

    # Build the test/exe; link via -L to our build dir
    ${CC} ${CPPFLAGS} ${CFLAGS} -I${S} ${S}/mydynamic.c \
        -L${B} -lmydynamic \
        -o ${B}/mydynamic \
        ${LDFLAGS}

    bbdebug 1 "Compilation done"
}

do_install() {
    bbdebug 1 "Installing"

    install -d ${D}${libdir}
    # Shared libs are typically 0644 (execute bit not required)
    install -m 0644 ${B}/${MYLIB}.so.${MYFULLVER} ${D}${libdir}/
    ln -sf ${MYLIB}.so.${MYFULLVER} ${D}${libdir}/${MYLIB}.so.${MYMAJOR}
    ln -sf ${MYLIB}.so.${MYMAJOR}   ${D}${libdir}/${MYLIB}.so

    install -d ${D}${bindir}
    install -m 0755 ${B}/mydynamic ${D}${bindir}/

    install -d ${D}${includedir}
    install -m 0644 ${S}/mylib.h ${D}${includedir}/

    bbdebug 1 "Installation done"
}

