using Infrastructure.Helpers;
using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Net;
using App.Interfaces;
using EFContext;
using App.Account;
using App.Exceptions;

namespace Infrastructure.Services
{
    public class UserService : IUserService
    {
        private readonly EFDataContext _context;
        public UserService(EFDataContext context)
        {
            _context = context;
        }
        public UserViewModel ChageUserImage(string userName, string image)
        {
            var user = _context.Users.SingleOrDefault(x=>x.UserName==userName);
            if (user == null)
            {
                throw new RestException(HttpStatusCode.NotFound);
            }

            string filename = Guid.NewGuid().ToString() + ".jpg";
            string path = Path.Combine(Directory.GetCurrentDirectory(), "ProfileImages");
            if (!Directory.Exists(path))
            {
                Directory.CreateDirectory(path);
            }
            path = Path.Combine(path, filename);
            if (string.IsNullOrEmpty(image))
            {
                return null;
            }

            using (Bitmap b = ImageWorker.Base64StringToBitmap(image))
            {
                Bitmap savedImage = ImageWorker.CreateImage(b, 400, 360);
                if (savedImage != null)
                {
                    savedImage.Save(path, ImageFormat.Jpeg);
                    user.Image = filename;
                    _context.SaveChanges();
                    return null;
                }
                else
                {
                    return null;
                }
            };
        }
    }
}
