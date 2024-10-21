import Foundation

@objc public class Foregroundservice: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
