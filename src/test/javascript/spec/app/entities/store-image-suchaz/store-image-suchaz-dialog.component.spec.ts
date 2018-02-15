/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { StoreImageSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/store-image-suchaz/store-image-suchaz-dialog.component';
import { StoreImageSuchazService } from '../../../../../../main/webapp/app/entities/store-image-suchaz/store-image-suchaz.service';
import { StoreImageSuchaz } from '../../../../../../main/webapp/app/entities/store-image-suchaz/store-image-suchaz.model';
import { StoreSuchazService } from '../../../../../../main/webapp/app/entities/store-suchaz';

describe('Component Tests', () => {

    describe('StoreImageSuchaz Management Dialog Component', () => {
        let comp: StoreImageSuchazDialogComponent;
        let fixture: ComponentFixture<StoreImageSuchazDialogComponent>;
        let service: StoreImageSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [StoreImageSuchazDialogComponent],
                providers: [
                    StoreSuchazService,
                    StoreImageSuchazService
                ]
            })
            .overrideTemplate(StoreImageSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StoreImageSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StoreImageSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new StoreImageSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.storeImage = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'storeImageListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new StoreImageSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.storeImage = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'storeImageListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
