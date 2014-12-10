/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vic.collaborativeClouds.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.QRCode;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import vic.collaborativeClouds.configs.QRCodeConfiguration;

/**
 *
 * @author CollaborativeClouds Software Solutions
 * <www.collaborativeclouds.com>
 * <collaborativeclouds@gmail.com>
 */
public class QRCodeGenerator {
    
    public void generate(String qrcode_data) {
        
        try {
            
            File mFile = new File(qrcode_data + ".png");
            Hashtable<EncodeHintType, ErrorCorrectionLevel> mHintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
            mHintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter mWriter = new QRCodeWriter();
            
            BitMatrix mBitMatrix = mWriter.encode(qrcode_data, QRCodeConfiguration.FORMAT, QRCodeConfiguration.SIZE, QRCodeConfiguration.SIZE);
            int mBitMatrixWidth = mBitMatrix.getWidth();
            BufferedImage mImage = new BufferedImage(mBitMatrixWidth, mBitMatrixWidth, BufferedImage.TYPE_INT_RGB);
            mImage.createGraphics();
            
            Graphics2D mGraphics = (Graphics2D) mImage.getGraphics();
            mGraphics.setColor(Color.WHITE);
            mGraphics.fillRect(0, 0, mBitMatrixWidth, mBitMatrixWidth);
            mGraphics.setColor(Color.BLACK);
            
            for (int i = 0; i < mBitMatrixWidth; i++) {
                for (int j = 0; j < mBitMatrixWidth; j++) {
                    if (mBitMatrix.get(i, j)) {
                        mGraphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(mImage, QRCodeConfiguration.FILETYPE, mFile);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "QRCode Generation Failed");
        }
    }
    
}
