import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { RelationshipSuchaz } from './relationship-suchaz.model';
import { RelationshipSuchazService } from './relationship-suchaz.service';

@Injectable()
export class RelationshipSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private relationshipService: RelationshipSuchazService

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
                this.relationshipService.find(id)
                    .subscribe((relationshipResponse: HttpResponse<RelationshipSuchaz>) => {
                        const relationship: RelationshipSuchaz = relationshipResponse.body;
                        this.ngbModalRef = this.relationshipModalRef(component, relationship);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.relationshipModalRef(component, new RelationshipSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    relationshipModalRef(component: Component, relationship: RelationshipSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.relationship = relationship;
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
