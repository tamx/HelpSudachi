//
//  HLPViewController.h
//  HelpSudachi
//
//  Created by Tetsuo Kawakami on 2013/11/04.
//  Copyright (c) 2013年 Yadorogi Software. All rights reserved.
//  http://yadorogi-soft.net


#import <UIKit/UIKit.h>

@interface HLPViewController : UIViewController
@property (strong, nonatomic) IBOutlet UIWebView *webView;
- (IBAction)reloadButton:(id)sender;

@end
