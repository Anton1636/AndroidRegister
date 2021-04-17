using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace App.Account.Login
{
    public class LoginValidation : AbstractValidator<LoginCommand>
    {
        public LoginValidation()
        {
            RuleFor(x => x.Email).NotEmpty().WithMessage("Поле не можу бути пустим");
            RuleFor(x => x.Password).NotEmpty().WithMessage("Поле не можу бути пустим");
        }
    }
}
