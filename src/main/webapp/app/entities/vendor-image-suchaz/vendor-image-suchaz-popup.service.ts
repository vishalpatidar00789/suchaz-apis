import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { VendorImageSuchaz } from './vendor-image-suchaz.model';
import { VendorImageSuchazService } from './vendor-image-suchaz.service';

@Injectable()
export class VendorImageSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private vendorImageService: VendorImageSuchazService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.vendorImageService.find(id)
                    .subscribe((vendorImageResponse: HttpResponse<VendorImageSuchaz>) => {
                        const vendorImage: VendorImageSuchaz = vendorImageResponse.body;
                        this.ngbModalRef = this.vendorImageModalRef(component, vendorImage);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.vendorImageModalRef(component, new VendorImageSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    vendorImageModalRef(component: Component, vendorImage: VendorImageSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.vendorImage = vendorImage;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
