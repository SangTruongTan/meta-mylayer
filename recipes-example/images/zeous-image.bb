SUMMARY = "A small boot image for LWL leaners"
LICENSE = "MIT"

inherit core-image

# Core files for basic console boot
IMAGE_INSTALL = "packagegroup-core-boot"
IMAGE_ROOTFS_SIZE ?= "16384"

# Add our needed applications
IMAGE_INSTALL += "usbutils"

IMAGE_FEATURES += "ssh-server-dropbear splash tools-sdk"
