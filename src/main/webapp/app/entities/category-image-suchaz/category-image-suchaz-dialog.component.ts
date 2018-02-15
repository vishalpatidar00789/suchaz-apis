import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { CategoryImageSuchaz } from './category-image-suchaz.model';
import { CategoryImageSuchazPopupService } from './category-image-suchaz-popup.service';
import { CategoryImageSuchazService } from './category-image-suchaz.service';
import { CategorySuchaz, CategorySuchazService } from '../category-suchaz';

@Component({
    selector: 'jhi-category-image-suchaz-dialog',
    templateUrl: './category-image-suchaz-dialog.component.html'
})
export class CategoryImageSuchazDialogComponent implements OnInit {

    categoryImage: CategoryImageSuchaz;
    isSaving: boolean;

    categories: CategorySuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private categoryImageService: CategoryImageSuchazService,
        private categoryService: CategorySuchazService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.categoryService.query()
            .subscribe((res: HttpResponse<CategorySuchaz[]>) => { this.categories = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
        this.dataUtils.clearInputImage(this.categoryImage, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.categoryImage.id !== undefined) {
            this.subscribeToSaveResponse(
                this.categoryImageService.update(this.categoryImage));
        } else {
            this.subscribeToSaveResponse(
                this.categoryImageService.create(this.categoryImage));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CategoryImageSuchaz>>) {
        result.subscribe((res: HttpResponse<CategoryImageSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CategoryImageSuchaz) {
        this.eventManager.broadcast({ name: 'categoryImageListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackCategoryById(index: number, item: CategorySuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-category-image-suchaz-popup',
    template: ''
})
export class CategoryImageSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private categoryImagePopupService: CategoryImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.categoryImagePopupService
                    .open(CategoryImageSuchazDialogComponent as Component, params['id']);
            } else {
                this.categoryImagePopupService
                    .open(CategoryImageSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
