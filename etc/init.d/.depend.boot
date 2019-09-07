TARGETS = console-setup mountkernfs.sh resolvconf ufw x11-common hostname.sh apparmor screen-cleanup plymouth-log udev keyboard-setup cryptdisks cryptdisks-early networking hwclock.sh open-iscsi iscsid mountdevsubfs.sh checkroot.sh lvm2 urandom checkfs.sh mountall-bootclean.sh mountall.sh bootmisc.sh procps mountnfs.sh kmod checkroot-bootclean.sh mountnfs-bootclean.sh
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
networking: mountkernfs.sh urandom resolvconf procps
hwclock.sh: mountdevsubfs.sh
open-iscsi: networking iscsid
iscsid: networking
mountdevsubfs.sh: mountkernfs.sh udev
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
lvm2: cryptdisks-early mountdevsubfs.sh udev
urandom: hwclock.sh
checkfs.sh: cryptdisks lvm2 checkroot.sh
mountall-bootclean.sh: mountall.sh
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
bootmisc.sh: mountall-bootclean.sh udev checkroot-bootclean.sh mountnfs-bootclean.sh
procps: mountkernfs.sh udev
mountnfs.sh: networking
kmod: checkroot.sh
checkroot-bootclean.sh: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
