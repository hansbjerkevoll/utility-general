//
//  Logger.swift
//  Utility
//
//  Created by Hans Bjerkevoll on 28/06/2020.
//  Copyright © 2020 hansbjerkevoll. All rights reserved.
//

import Foundation

public struct Default {
	public static let Info		= "INFO:"
	public static let Warning	= "WARNING:"
	public static let Error 	= "ERROR:"
}

public struct Logger {
	public static func info(_ object: Any, functionName: String = #function, fileName: String = #file, lineNumber: Int = #line) {
		outputLog(object: object, logType: Default.Info, functionName: functionName, fileName: fileName, lineNumber: lineNumber)
	}

	public static func warning(_ object: Any, functionName: String = #function, fileName: String = #file, lineNumber: Int = #line) {
		outputLog(object: object, logType: Default.Warning, functionName: functionName, fileName: fileName, lineNumber: lineNumber)
	}

	public static func error(_ object: Any, functionName: String = #function, fileName: String = #file, lineNumber: Int = #line) {
		outputLog(object: object, logType: Default.Error, functionName: functionName, fileName: fileName, lineNumber: lineNumber)	}

	public static func outputLog(object: Any, logType: String, functionName: String, fileName: String, lineNumber: Int) {
	  #if DEBUG
		let className = (fileName as NSString).lastPathComponent
		print("\(logType) <\(className)> \(functionName) [#\(lineNumber)]: \(object)\n")
	  #endif
	}
}
