using Microsoft.EntityFrameworkCore;
using HVK.Models;
using Microsoft.AspNetCore.Authentication.Cookies;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddDistributedMemoryCache();
builder.Services.AddTransient<FormattingService>();
builder.Services.AddControllersWithViews();
builder.Services.AddDbContext<HVKW24_Team3Context>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("MyConnection"))
);
builder.Services.AddSession(options =>
    options.IdleTimeout = TimeSpan.FromMinutes(15)
);
builder.Services.AddAuthentication(CookieAuthenticationDefaults.AuthenticationScheme).AddCookie();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment()) {
    app.UseExceptionHandler("/Home/Error");
}
app.UseStaticFiles();

app.UseCookiePolicy();

app.UseRouting();

app.UseAuthentication();

app.UseAuthorization();
app.UseSession();

app.UseSession();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Account}/{action=Login}/{id?}");

app.Run();
