//
//  HLPViewController.m
//  HelpSudachi
//
//  Created by Tetsuo Kawakami on 2013/11/04.
//  Copyright (c) 2013年 Yadorogi Software. All rights reserved.
//  http://yadorogi-soft.net

#import "HLPViewController.h"

@interface HLPViewController ()

@end

@implementation HLPViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    
    NSURL  *detail_url = [NSURL URLWithString:@"http://192.168.xxx.xxx:8888"];  // notice !!
    NSURLRequest *URLreq = [NSURLRequest requestWithURL:detail_url];
    [_webView loadRequest:URLreq];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)reloadButton:(id)sender {
    [_webView reload];
}

@end
