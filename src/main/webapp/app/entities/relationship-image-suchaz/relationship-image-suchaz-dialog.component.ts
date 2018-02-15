import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { RelationshipImageSuchaz } from './relationship-image-suchaz.model';
import { RelationshipImageSuchazPopupService } from './relationship-image-suchaz-popup.service';
import { RelationshipImageSuchazService } from './relationship-image-suchaz.service';
import { RelationshipSuchaz, RelationshipSuchazService } from '../relationship-suchaz';

@Component({
    selector: 'jhi-relationship-image-suchaz-dialog',
    templateUrl: './relationship-image-suchaz-dialog.component.html'
})
export class RelationshipImageSuchazDialogComponent implements OnInit {

    relationshipImage: RelationshipImageSuchaz;
    isSaving: boolean;

    relationships: RelationshipSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private relationshipImageService: RelationshipImageSuchazService,
        private relationshipService: RelationshipSuchazService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.relationshipService.query()
            .subscribe((res: HttpResponse<RelationshipSuchaz[]>) => { this.relationships = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.relationshipImage, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.relationshipImage.id !== undefined) {
            this.subscribeToSaveResponse(
                this.relationshipImageService.update(this.relationshipImage));
        } else {
            this.subscribeToSaveResponse(
                this.relationshipImageService.create(this.relationshipImage));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<RelationshipImageSuchaz>>) {
        result.subscribe((res: HttpResponse<RelationshipImageSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: RelationshipImageSuchaz) {
        this.eventManager.broadcast({ name: 'relationshipImageListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackRelationshipById(index: number, item: RelationshipSuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-relationship-image-suchaz-popup',
    template: ''
})
export class RelationshipImageSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private relationshipImagePopupService: RelationshipImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.relationshipImagePopupService
                    .open(RelationshipImageSuchazDialogComponent as Component, params['id']);
            } else {
                this.relationshipImagePopupService
                    .open(RelationshipImageSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
