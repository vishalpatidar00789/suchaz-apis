import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { RelationshipImageSuchaz } from './relationship-image-suchaz.model';
import { RelationshipImageSuchazService } from './relationship-image-suchaz.service';

@Injectable()
export class RelationshipImageSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private relationshipImageService: RelationshipImageSuchazService

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
                this.relationshipImageService.find(id)
                    .subscribe((relationshipImageResponse: HttpResponse<RelationshipImageSuchaz>) => {
                        const relationshipImage: RelationshipImageSuchaz = relationshipImageResponse.body;
                        this.ngbModalRef = this.relationshipImageModalRef(component, relationshipImage);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.relationshipImageModalRef(component, new RelationshipImageSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    relationshipImageModalRef(component: Component, relationshipImage: RelationshipImageSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.relationshipImage = relationshipImage;
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
