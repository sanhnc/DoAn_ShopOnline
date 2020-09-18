using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace WebAPI_BanHangOnline.Models
{
    public partial class ShopOnlineContext : DbContext
    {
        public ShopOnlineContext()
        {
        }

        public ShopOnlineContext(DbContextOptions<ShopOnlineContext> options)
            : base(options)
        {
        }

        public virtual DbSet<ChiTietHoaDon> ChiTietHoaDon { get; set; }
        public virtual DbSet<HoaDon> HoaDon { get; set; }
        public virtual DbSet<KhachHangOnline> KhachHangOnline { get; set; }
        public virtual DbSet<LoaiSp> LoaiSp { get; set; }
        public virtual DbSet<SanPham> SanPham { get; set; }
        public virtual DbSet<TaiKhoan> TaiKhoan { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
                optionsBuilder.UseSqlServer("Data Source=KILL\\SQLEXPRESS;Initial Catalog=ShopOnline;Integrated Security=True");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<ChiTietHoaDon>(entity =>
            {
                entity.HasKey(e => new { e.MaHd, e.MaSp });

                entity.Property(e => e.MaHd)
                    .HasColumnName("MaHD")
                    .HasMaxLength(10)
                    .IsFixedLength();

                entity.Property(e => e.MaSp)
                    .HasColumnName("MaSP")
                    .HasMaxLength(20);

                entity.HasOne(d => d.MaHdNavigation)
                    .WithMany(p => p.ChiTietHoaDon)
                    .HasForeignKey(d => d.MaHd)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_ChiTietHoaDon_HoaDon");

                entity.HasOne(d => d.MaSpNavigation)
                    .WithMany(p => p.ChiTietHoaDon)
                    .HasForeignKey(d => d.MaSp)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_ChiTietHoaDon_SanPham");
            });

            modelBuilder.Entity<HoaDon>(entity =>
            {
                entity.HasKey(e => e.MaHd);

                entity.Property(e => e.MaHd)
                    .HasColumnName("MaHD")
                    .HasMaxLength(10)
                    .IsFixedLength();

                entity.Property(e => e.MaKh).HasColumnName("MaKH");

                entity.Property(e => e.NgayGiao).HasMaxLength(50);

                entity.Property(e => e.NgayLap).HasMaxLength(50);

                entity.HasOne(d => d.MaKhNavigation)
                    .WithMany(p => p.HoaDon)
                    .HasForeignKey(d => d.MaKh)
                    .HasConstraintName("FK_HoaDon_KhachHangOnline");
            });

            modelBuilder.Entity<KhachHangOnline>(entity =>
            {
                entity.HasKey(e => e.MaKh);

                entity.Property(e => e.MaKh).HasColumnName("MaKH");

                entity.Property(e => e.DiaChi).HasMaxLength(50);

                entity.Property(e => e.Email).HasMaxLength(50);

                entity.Property(e => e.HoTen).HasMaxLength(50);

                entity.Property(e => e.MatKhau).HasMaxLength(50);

                entity.Property(e => e.Sdt)
                    .HasColumnName("SDT")
                    .HasMaxLength(50);
            });

            modelBuilder.Entity<LoaiSp>(entity =>
            {
                entity.HasKey(e => e.MaLoaiSp);

                entity.ToTable("LoaiSP");

                entity.Property(e => e.MaLoaiSp)
                    .HasColumnName("MaLoaiSP")
                    .HasMaxLength(10);

                entity.Property(e => e.TenLoaiSp).HasColumnName("TenLoaiSP");
            });

            modelBuilder.Entity<SanPham>(entity =>
            {
                entity.HasKey(e => e.MaSp);

                entity.Property(e => e.MaSp)
                    .HasColumnName("MaSP")
                    .HasMaxLength(20);

                entity.Property(e => e.DonViTinh).HasMaxLength(10);

                entity.Property(e => e.MaLoaiSp)
                    .HasColumnName("MaLoaiSP")
                    .HasMaxLength(10);

                entity.Property(e => e.TenSp)
                    .HasColumnName("TenSP")
                    .HasMaxLength(50);

                entity.HasOne(d => d.MaLoaiSpNavigation)
                    .WithMany(p => p.SanPham)
                    .HasForeignKey(d => d.MaLoaiSp)
                    .HasConstraintName("FK_SanPham_LoaiSP");
            });

            modelBuilder.Entity<TaiKhoan>(entity =>
            {
                entity.HasKey(e => e.TenDn);

                entity.Property(e => e.TenDn)
                    .HasColumnName("TenDN")
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.MatKhau)
                    .HasMaxLength(50)
                    .IsUnicode(false);
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
