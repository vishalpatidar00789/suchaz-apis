/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { OccasionImageSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/occasion-image-suchaz/occasion-image-suchaz-dialog.component';
import { OccasionImageSuchazService } from '../../../../../../main/webapp/app/entities/occasion-image-suchaz/occasion-image-suchaz.service';
import { OccasionImageSuchaz } from '../../../../../../main/webapp/app/entities/occasion-image-suchaz/occasion-image-suchaz.model';
import { OccassionSuchazService } from '../../../../../../main/webapp/app/entities/occassion-suchaz';

describe('Component Tests', () => {

    describe('OccasionImageSuchaz Management Dialog Component', () => {
        let comp: OccasionImageSuchazDialogComponent;
        let fixture: ComponentFixture<OccasionImageSuchazDialogComponent>;
        let service: OccasionImageSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [OccasionImageSuchazDialogComponent],
                providers: [
                    OccassionSuchazService,
                    OccasionImageSuchazService
                ]
            })
            .overrideTemplate(OccasionImageSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OccasionImageSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OccasionImageSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new OccasionImageSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.occasionImage = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'occasionImageListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new OccasionImageSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.occasionImage = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'occasionImageListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
