export class User{
        id:any;
        firstname:any;
        lastname:any;
        email:any;
        password:any;
        role:any;
        logged:any;
        repeatPassword: any;

}


// constructor(public userService:UserService,private router:Router) {}
//     user: User = new User();

//   onSubmit(form: NgForm) {
//     this.userService.register(form.value).subscribe(
//         (res: any) => {
//                 alert("new user registered..")
//                 this.resetForm(form);
//                 this.router.navigateByUrl("/login");
//             },
//               (err: any) => {
//                 alert(err.error.message);
//                 console.log(err.error.message);
      
//             }
//             );
//   }

//   resetForm(form: NgForm) {
//     form.resetForm();
//     this.user = new User();
//   }


// onSubmit(form: NgForm) {
//         // console.log("outside : " + JSON.stringify(form));
//         this.userService.register(form.value).subscribe(
//           (res: any) => {
//             alert("new user registered..")
//             this.resetForm(form);
//             this.router.navigateByUrl("/login");
//         },
//           (err: any) => {
//             alert(err.error.message);
//             console.log(err.error.message);
  
//         }
//         );
//     }