import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { OccasionImageSuchaz } from './occasion-image-suchaz.model';
import { OccasionImageSuchazService } from './occasion-image-suchaz.service';

@Injectable()
export class OccasionImageSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private occasionImageService: OccasionImageSuchazService

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
                this.occasionImageService.find(id)
                    .subscribe((occasionImageResponse: HttpResponse<OccasionImageSuchaz>) => {
                        const occasionImage: OccasionImageSuchaz = occasionImageResponse.body;
                        this.ngbModalRef = this.occasionImageModalRef(component, occasionImage);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.occasionImageModalRef(component, new OccasionImageSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    occasionImageModalRef(component: Component, occasionImage: OccasionImageSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.occasionImage = occasionImage;
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
