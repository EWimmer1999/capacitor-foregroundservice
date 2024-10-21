// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorForegroundservice",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "CapacitorForegroundservice",
            targets: ["ForegroundservicePlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "ForegroundservicePlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/ForegroundservicePlugin"),
        .testTarget(
            name: "ForegroundservicePluginTests",
            dependencies: ["ForegroundservicePlugin"],
            path: "ios/Tests/ForegroundservicePluginTests")
    ]
)